import { style } from '../styler.js';

style(`
    .details-drawer {
      background: var(--lumo-base-color);
      flex-direction: column;
      height: 100%;
      max-height: 100%;
      max-width: 100%;
      overflow: hidden;
      /* transition: all var(--transition-duration-s); */
      z-index: 0;
    }

    .details-drawer[open] {
      box-shadow: var(--lumo-box-shadow-m);
    }

    .details-drawer__header {
      flex-shrink: 0;
    }

    .details-drawer__content {
      flex: 1;
      overflow: auto;
      -webkit-overflow-scrolling: touch;
    }

    .details-drawer__footer {
      flex-shrink: 0;
    }

    @media(max-width: 719px) {
      .details-drawer {
        left: 0;
        margin: 0;
        min-width: 100%;
        position: fixed;
        top: 0;
        z-index: 3;
      }

      .details-drawer:not([open])[position="right"] {
        transform: translateX(100%);
      }

      .details-drawer:not([open])[position="bottom"] {
        transform: translateY(100%);
      }
    }

    @media(min-width: 720px) {
      .details-drawer[position="bottom"] {
        height: 400px;
      }

      .details-drawer:not([open])[position="bottom"] {
        margin-bottom: -400px;
      }

      .details-drawer[position="right"] {
        width: var(--details-drawer-width);
      }

      .details-drawer:not([open])[position="right"] {
        margin-right: calc(var(--details-drawer-width) * -1);
      }
    }
 `)