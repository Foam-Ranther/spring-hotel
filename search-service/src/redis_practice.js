import { createClient } from "redis";

const client = createClient({
  url: "redis://localhost:6379",
});

client.on("error", (err) => console.error("client error", err));
await client.connect();
console.log(client);
const users = ["yash", "amy"];
await client.set("users", JSON.stringify(users), {
  EX: 20,
});

const data = await client.get("users");
console.log(
  `retreived data : ${JSON.parse(data)}, time left ${await client.ttl("users")}`,
);

// client.del("user:101");
client.disconnect();
