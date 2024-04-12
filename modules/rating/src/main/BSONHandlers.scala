package lila.rating

import reactivemongo.api.bson.BSONHandler

import lila.db.dsl.given
import lila.core.perf.PerfId
import lila.rating.PerfType

object BSONHandlers:

  given perfTypeIdHandler: BSONHandler[PerfType] =
    summon[BSONHandler[PerfId]].as[PerfType](
      id => PerfType.byId.get(id).err(s"Unknown perf id $id"),
      _.id
    )

  given perfTypeKeyHandler: BSONHandler[PerfType] =
    summon[BSONHandler[PerfKey]].as[PerfType](
      key => PerfType(key).err(s"Unknown perf type $key"),
      _.key
    )
