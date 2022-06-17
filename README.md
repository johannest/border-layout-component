# Border Layout component

Vaadin version of Java BorderLayout component with rather good API compatibility to Java version.

Uses CSS Grid which can be configured with the following variables. The default value for the default and the HEADER_MAIN_FOOTER theme variant are presented below.

### Default BorderLayout
Grid rows:
```
--header-height: 1fr;
--center-height: 1fr;
--footer-height: 1fr;
```
Grid columns:
```
--left-width: 1fr;
--center-width: 1fr;
--right-width:  1fr;
```

### BorderLayoutVariant.HEADER_MAIN_FOOTER
Grid rows:
```
--header-height: auto;
--center-height: 1fr;
--footer-height: auto;
```

Grid columns:
```
--left-width: 1fr;
--center-width: auto;
--right-width:  auto;
```

### Other variables
* Border: `--layout-border`
* Gap between rows: `--row-gap` (also Java API: `setHgap(int hgap)`)
* Gap between columns: `--column-gap` (also Java API: `setVgap(int vgap)`)

## Development instructions

Starting the test/demo server:
1. Run `mvn jetty:run`.
2. Open http://localhost:8080 in the browser.
