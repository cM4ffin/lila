import type { Redraw } from 'common/snabbdom';
import { DasherCtrl } from './ctrl';
import { json as xhrJson } from 'common/xhr';
import { spinnerVdom, spinnerHtml } from 'common/spinner';
import { getSafariMajorVersion } from 'common/device';
import { init as initSnabbdom, type VNode, classModule, attributesModule, h } from 'snabbdom';

const patch = initSnabbdom([classModule, attributesModule]);

export function load(): Promise<DasherCtrl> {
  return site.asset.loadEsm<DasherCtrl>('dasher');
}

export default async function initModule(): Promise<DasherCtrl> {
  let vnode: VNode,
    ctrl: DasherCtrl | undefined = undefined;

  const $el = $('#dasher_app').html(`<div class="initiating">${spinnerHtml}</div>`);
  const element = $el.empty()[0] as HTMLElement;

  const redraw: Redraw = () => {
    vnode = patch(
      vnode || element,
      h('div#dasher_app.dropdown', ctrl?.render() ?? h('div.initiating', spinnerVdom())),
    );

    const safari = getSafariMajorVersion();
    if (safari && safari < 18) {
      const el = document?.querySelector("#dasher_app .sound input[type='range']");
      if (el) {
        (el as HTMLElement).style.appearance = 'slider-vertical';
      }
    }
  };

  redraw();

  const data = await xhrJson('/dasher');
  ctrl = new DasherCtrl(data, redraw);
  redraw();

  return ctrl;
}
