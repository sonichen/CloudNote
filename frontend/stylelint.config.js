module.exports = {
  extends: [
    // Use the Standard config as the base
    // https://github.com/stylelint/stylelint-config-standard
    'stylelint-config-standard',
    // Enforce a standard order for CSS properties
    // https://github.com/stormwarning/stylelint-config-recess-order
    'stylelint-config-recess-order',
    'stylelint-config-recommended-scss'],
  rules: {
    'max-nesting-depth': 4,
    'no-descending-specificity': null,
    'selector-pseudo-element-no-unknown': [true,
      {
        ignorePseudoElements: ['/^v-/', 'deep']
      }
    ]
  }
}
