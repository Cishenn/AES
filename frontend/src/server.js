import { createServer, Model } from 'miragejs'

export function makeServer ({ environment = 'development' } = {}) {
  const server = createServer({
    environment,
    models: {
      test: Model
    },

    routes () {
      this.namespace = 'api'

      this.get('/tests', (schema, request) => {
        return schema.tests.all()
      })
    },

    seeds (server) {
      server.create('test', { name: 'Mirage test successfully' })
    }
  })
  return server
}
