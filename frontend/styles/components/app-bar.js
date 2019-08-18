import { style } from '../styler.js';

style(`
    .app-bar {
      box-shadow: var(--lumo-box-shadow-m);
      flex-direction: column;
      position: relative;
      z-index: 1;
    }

    /* Container */
    .app-bar__container {
      padding: 0 var(--lumo-space-r-l);
      transition: padding var(--transition-duration-m);
    }

    /* Navi icon */
    .app-bar__navi-icon,
    .app-bar__context-icon {
      margin-right: var(--lumo-space-l);
    }

    /* Title */
    .app-bar__title {
      flex-grow: 1;
      margin: 0;
      overflow: hidden;
      text-overflow: ellipsis;
      white-space: nowrap;
    }

    /* Action items */
    .app-bar__action-items > *:not(:last-child) {
      margin-right: var(--lumo-space-s);
    }

    /* Search */
    .app-bar vaadin-text-field {
      padding: 0;
    }

    /* Avatar */
    .app-bar__avatar {
      border-radius: 100%;
      flex-shrink: 0;
      height: var(--lumo-size-s);
      margin-left: var(--lumo-space-m);
      width: var(--lumo-size-s);
    }

    /* Tabs */
    .app-bar__tabs {
      box-shadow: none;
    }

    .app-bar__tab vaadin-button {
      margin: 0;
    }

    .app-bar__add-tab {
      flex-shrink: 0;
    }

    @media (max-width: 1023px) {
      .app-bar__navi-icon,
      .app-bar__context-icon {
        margin-bottom: calc(calc(var(--app-bar-height-mobile) - var(--lumo-icon-size-m)) / 2);
        margin-top: calc(calc(var(--app-bar-height-mobile) - var(--lumo-icon-size-m)) / 2);
      }

      .app-bar__title:not(:empty) {
        margin-bottom: calc(calc(var(--app-bar-height-mobile) - calc(var(--lumo-font-size-l) * var(--lumo-line-height-xs))) / 2);
        margin-top: calc(calc(var(--app-bar-height-mobile) - calc(var(--lumo-font-size-l) * var(--lumo-line-height-xs))) / 2);
      }

      .app-bar__tab-container {
        padding: 0 var(--lumo-space-m);
      }
    }

    @media (min-width: 1024px) {
      .app-bar__navi-icon {
        display: none;
      }

      .app-bar__context-icon {
        margin-bottom: calc(calc(var(--app-bar-height-desktop) - var(--lumo-icon-size-m)) / 2);
        margin-top: calc(calc(var(--app-bar-height-desktop) - var(--lumo-icon-size-m)) / 2);
      }

      .app-bar__title:not(:empty) {
        margin-bottom: calc(calc(var(--app-bar-height-desktop) - calc(var(--lumo-font-size-l) * var(--lumo-line-height-xs))) / 2);
        margin-top: calc(calc(var(--app-bar-height-desktop) - calc(var(--lumo-font-size-l) * var(--lumo-line-height-xs))) / 2);
      }

      .app-bar__tab-container {
        padding: 0 var(--lumo-space-l);
      }
    }
`)
