import { createClient } from "redis";

const main = async () => {
  const client = createClient({
    url: "redis://localhost:6379", 
  });

  client.on("error", (err) => console.error("Redis Client Error", err));
  await client.connect();

  while(true) {
    const receiptDetails = await client.blPop("queue", 0); 
    console.log("Generating receipt for ",receiptDetails); 
  }
};

main();
