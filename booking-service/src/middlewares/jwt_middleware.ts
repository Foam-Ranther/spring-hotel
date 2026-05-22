import { Context, Next } from "hono";
import {verify} from "hono/jwt"

const SECRET = "9a4f2c8d3b7a1e6f5c8d9a0b2c3d4e5f6a7b8c9d0e1f2a3b4c5d6e7f8a9b0c1d"

export const authMiddleware = async (
  ctxt: Context,
  next: Next
) => {

  const token = ctxt.req.header("Authorization")?.split(" ").at(1); 
  console.log("Bearer Token:", token);

  if (!token) {
    return ctxt.json({ error: "Unauthorized" }, 401);
  }

  try {
    const payload = await verify(token, SECRET, "HS256");
    console.log(payload, Deno.env.get("JWT_SECRET"));
    ctxt.set("jwtPayload", payload);
    await next();
  } catch {
    return ctxt.json({ error: "Invalid token" }, 401);
  }
};