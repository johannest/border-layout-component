import {ThemableMixin} from '@vaadin/vaadin-themable-mixin/vaadin-themable-mixin.js';
import {customElement, html, css, LitElement, property} from 'lit-element';

@customElement('border-layout')
export class BorderLayout extends ThemableMixin(LitElement) {

    @property()
    theme : string | null = null;

    static get is() {
        return 'border-layout';
    }

    static get styles() {
        return css`
          :host {
             --header-height: 1fr;
             --center-height: 1fr;
             --footer-height: 1fr;
             --left-width: 1fr;
             --center-width: 1fr;
             --right-width: 1fr;
          }
          :host([theme~="header-main-footer"]) {
               --header-height: auto;
               --center-height: 1fr;
               --footer-height: auto;
               --left-width: 1fr;
               --center-width: auto;
               --right-width: auto;
          }
          :host([theme~="framed"]) {
               --layout-border: 1px solid lightgray;
          }
          #layout {
              display: grid;
              grid-template-rows: var(--header-height) var(--center-height) var(--footer-height);
              grid-template-columns: var(--left-width) var(--center-width) var(--right-width);
              border: var(--layout-border);
              row-gap: var(--row-gap);
              column-gap: var(--column-gap);
          }
          section[theme~="header-main-footer"] {
              height: 100vh !important;
          }
          #layout #header {
            overflow: hidden;
            grid-column: 1 / -1;
            grid-row: 1 / 2;
            border: var(--layout-border);
          }
          #layout #lnav {
            overflow: hidden;
            grid-column: 1 / 2;
            grid-row: 2 / 3;
            border: var(--layout-border);
          }
          #layout #main {
            overflow: auto;
            grid-column: 2 / 3;
            grid-row: 2 / 3;
            border: var(--layout-border);
          }
          #layout #rnav {
            overflow: hidden;
            grid-column: 3 / 4;
            grid-row: 2 / 3;
            border: var(--layout-border);
          }
          #layout #footer {
            overflow: hidden;
            grid-column: 1 / -1;
            grid-row: 3 / 4;
            border: var(--layout-border);
          }
        `;
    }

    render() {
        return html`
            <section id="layout" theme="${this.theme}">
                <header id="header">
                    <slot id="NORTH" name="NORTH">
                    </slot>
                </header>
                <nav id="lnav">
                    <slot id="WEST" name="WEST">
                    </slot>
                </nav>
                <main id="main">
                    <slot id="CENTER" name="CENTER">
                    </slot>
                </main>
                <nav id="rnav">
                    <slot id="EAST" name="EAST">
                    </slot>
                </nav>
                <footer id="footer">
                    <slot id="SOUTH" name="SOUTH">
                    </slot>
                </footer>
            </section>
        `;
    }
}
