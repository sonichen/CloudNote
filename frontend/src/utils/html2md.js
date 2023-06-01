import TurndownService from 'turndown'
const turndown = new TurndownService({
  emDelimiter: '_',
  linkStyle: 'inlined',
  headingStyle: 'atx'
})

export default turndown
