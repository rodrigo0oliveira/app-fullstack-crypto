import { defineConfig } from "cypress";
const cucumber = require('cypress-cucumber-preprocessor').default

export default defineConfig({
  chromeWebSecurity:false,
  e2e: {
    setupNodeEvents(on, config) {
      on("file:preprocessor", cucumber());
    },
    baseUrl: "http://localhost:4200",
    testIsolation:false, //para que não atualize a página após cada teste ser realizado.
    specPattern:"cypress/e2e/step_definitions/*.feature",
  },
});
