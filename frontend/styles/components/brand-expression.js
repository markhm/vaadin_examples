import { style } from '../styler.js';

style(`
    .brand-expression {
      align-items: center;
      box-shadow: inset 0 -1px var(--lumo-contrast-20pct);
      box-sizing: border-box;
      display: flex;
      /* Application header height with tabs */
      height: calc(var(--app-bar-height-desktop) + var(--lumo-size-l));
      justify-content: center;
      padding: var(--lumo-space-m);
    }

    .brand-expression__logo {
      max-height: 100%;
      max-width: 100%;
    }

    .brand-expression__title {
      margin-left: var(--lumo-space-s);
    }

    .navi-drawer[rail] .brand-expression__title {
      display: none;
    }
`)