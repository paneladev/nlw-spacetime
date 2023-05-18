import 'dotenv/config'

import fastify from 'fastify'
import { memoriesRoutes } from './routes/memories'
import cors from '@fastify/cors'
import jwt from '@fastify/jwt'
import { authRoutes } from './routes/auth'

const app = fastify()

app.register(cors, {
  origin: true, // todas urls de front podem acessar nosso backend
  // origin: ['http://localhost:3000']
})

app.register(jwt, {
  secret: 'paneladev',
})

app.register(authRoutes)
app.register(memoriesRoutes)

app
  .listen({
    port: 3333,
  })
  .then(() => {
    console.log('✌ HTTP server running on http://localhost:3333')
  })
