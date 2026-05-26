import {MongoClient} from "mongodb";

const dbUrl:string = Deno.env.get("MONGO_URL") || "mongodb://localhost:27017/spring-hotel";

const client = new MongoClient(dbUrl);
try {
    await client.connect();
    console.log(" \n Mongo connected \n");
} catch (err) {
    console.error("Mongo connection failed:", err);
}
const db = client.db("spring-hotel");

export default  db