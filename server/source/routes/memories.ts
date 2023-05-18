import { FastifyInstance } from 'fastify'
import { z } from 'zod'
import { prisma } from '../lib/prisma'

export async function memoriesRoutes(app: FastifyInstance) {
  // verifica se esta autenticado antes da chamada de cada rota
  app.addHook('preHandler', async (request) => {
    await request.jwtVerify()
  })

  app.get('/memories', async (request) => {
    const user = request.user

    const memories = await prisma.memory.findMany({
      where: {
        userId: user.sub,
      },
      orderBy: {
        createdAt: 'asc',
      },
    })
    return memories.map((memory) => {
      return {
        id: memory.id,
        coverUrl: memory.coverUrl,
        excerpt: memory.content.substring(0, 115).concat('...'),
      }
    })
  })

  app.get('/memories/:id', async (request, response) => {
    const user = request.user

    const paramSchema = z.object({
      id: z.string().uuid(),
    })
    const { id } = paramSchema.parse(request.params)

    const memory = await prisma.memory.findUniqueOrThrow({
      where: {
        id,
      },
    })

    if (!memory.isPublic && memory.userId !== user.sub) {
      return response.status(401).send()
    }

    return memory
  })

  app.post('/memories', async (request) => {
    const user = request.user

    const bodySchema = z.object({
      content: z.string(),
      coverUrl: z.string(),
      isPublic: z.coerce.boolean().default(false),
    })

    const { content, coverUrl, isPublic } = bodySchema.parse(request.body)

    const memory = await prisma.memory.create({
      data: {
        content,
        coverUrl,
        isPublic,
        userId: user.sub,
      },
    })
    return memory
  })

  app.put('/memories/:id', async (request, response) => {
    const paramSchema = z.object({
      id: z.string().uuid(),
    })
    const { id } = paramSchema.parse(request.params)

    const bodySchema = z.object({
      content: z.string(),
      coverUrl: z.string(),
      isPublic: z.coerce.boolean().default(false),
    })

    const { content, coverUrl, isPublic } = bodySchema.parse(request.body)
    const user = request.user

    let memory = await prisma.memory.findUniqueOrThrow({
      where: {
        id,
      },
    })

    if (memory.userId !== user.sub) {
      return response.status(401).send()
    }

    memory = await prisma.memory.update({
      where: {
        id,
      },
      data: {
        content,
        coverUrl,
        isPublic,
      },
    })
    return memory
  })

  app.delete('/memories/:id', async (request, response) => {
    const paramSchema = z.object({
      id: z.string().uuid(),
    })
    const { id } = paramSchema.parse(request.params)
    const user = request.user

    const memory = await prisma.memory.findUniqueOrThrow({
      where: {
        id,
      },
    })

    if (memory.userId !== user.sub) {
      return response.status(401).send()
    }

    await prisma.memory.delete({
      where: {
        id,
      },
    })
  })
}
