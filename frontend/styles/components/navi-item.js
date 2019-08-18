import { style } from "../styler.js";

style(`
    .navi-item {
      align-items: center;
      display: flex;
      font-size: var(--lumo-font-size-s);
      font-weight: 600;
      height: var(--lumo-size-l);
      transition: background-color var(--transition-duration-s);
    }

    /* Sub items */
    .navi-item[level] {
      background-color: var(--lumo-contrast-5pct);
      font-size: var(--lumo-font-size-xs);
      font-weight: normal;
      height: var(--lumo-size-m);
    }

    .navi-item[level="1"] label {
      font-weight: 500;
      margin-left: var(--navi-item-indentation);
    }

    .navi-item[level="2"] label {
      margin-left: calc(var(--navi-item-indentation) + var(--lumo-space-m));
    }

    .navi-item[level="3"] label {
      font-weight: 300;
      margin-left: calc(var(--navi-item-indentation) + calc(var(--lumo-space-m) * 2));
    }

    /* Hover */
    .navi-item:hover {
      background-color: var(--lumo-contrast-10pct);
    }

    .navi-item__link:hover {
      text-decoration: none;
    }

    /* Active */
    .navi-item:active {
      background-color: var(--lumo-contrast-20pct);
    }

    /* Link */
    .navi-item__link {
      align-items: center;
      display: flex;
      flex-grow: 1;
      height: 100%;
      overflow: hidden;
      padding: 0 var(--lumo-space-m);
    }

    /* Link highlight */
    .navi-item__link:not([highlight]) {
      color: var(--lumo-body-text-color);
    }

    .navi-item__link:not([highlight]) iron-icon {
      color: var(--lumo-tertiary-text-color);
    }

    /* Link icon */
    .navi-item__link iron-icon {
      height: var(--lumo-icon-size-s);
      flex-shrink: 0;
      margin: 0 var(--lumo-space-l) 0 0;
      transition: margin var(--transition-duration-s);
      width: var(--lumo-icon-size-s);
    }

    .navi-item__link > div:first-child {
      display: flex;
    }

    /* Link label */
    .navi-item__link label {
      cursor: inherit;
      overflow: hidden;
      text-overflow: ellipsis;
      user-select: none;
      -webkit-user-select: none;
      white-space: nowrap;
    }

    /* Expand & collapse button */
    .navi-item vaadin-button {
      flex-shrink: 0;
      margin-left: auto;
      margin-right: var(--lumo-space-s);
    }
`);
