package controllers

import play.api.mvc.*
import views.*
import lila.app.{ given, * }
import lila.fide.Federation

final class Fide(env: Env) extends LilaController(env):

  private def WIP(f: Fu[Result])(using Context): Fu[Result] =
    if env.api.mode == play.api.Mode.Dev || isGrantedOpt(_.LichessTeam)
    then f
    else notFound

  def index(page: Int) = Open:
    WIP:
      Reasonable(page):
        for
          players      <- env.fide.paginator.best(page)
          renderedPage <- renderPage(html.fide.player.index(players))
        yield Ok(renderedPage)

  def show(id: chess.FideId, slug: String, page: Int) = Open:
    WIP:
      Found(env.fide.repo.player.fetch(id)): player =>
        if player.slug != slug then Redirect(routes.Fide.show(id, player.slug))
        else
          for
            tours    <- env.relay.playerTour.playerTours(player, page)
            rendered <- renderPage(html.fide.player.show(player, tours))
          yield Ok(rendered)

  def federations(page: Int) = Open:
    WIP:
      for
        feds         <- env.fide.paginator.federations(page)
        renderedPage <- renderPage(html.fide.federation.index(feds))
      yield Ok(renderedPage)

  def federation(slug: String, page: Int) = Open:
    WIP:
      Found(env.fide.federationApi.find(slug)): fed =>
        val fedSlug = Federation.nameToSlug(fed.name)
        if slug != fedSlug then Redirect(routes.Fide.federation(fedSlug))
        else
          for
            players  <- env.fide.paginator.federationPlayers(fed, page)
            rendered <- renderPage(html.fide.federation.show(fed, players))
          yield Ok(rendered)
