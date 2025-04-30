const { defineConfig } = require("cypress");

module.exports = defineConfig({
  e2e: {
    setupNodeEvents(on, config) {
      
    },
    baseUrl: "http://localhost:4200",
    testIsolation:false, //para que não atualize a página após cada teste ser realizado.
    specPattern:"cypress/e2e/step_definitions/**/*.js"
  },
});
