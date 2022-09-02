import { createMachine } from 'xstate'

export default createMachine({
  id: 'editBill',
  initial: 'idle',
  context: {
    retries: 0,
  },
  states: {
    idle: {
      on: {
        NEXT: 'people',
      },
    },
    people: {
      on: {
        NEXT: 'items',
        PREV: 'idle',
      },
    },
    items: {
      on: {
        NEXT: 'results',
        PREV: 'people',
      },
    },
    results: {
      type: 'final',
    },
  },
})
