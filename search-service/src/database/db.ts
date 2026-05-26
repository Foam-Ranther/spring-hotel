import {MongoClient} from "mongodb";

const dbUrl:string = Deno.env.get("MONGO_URL") || "mongodb://127.0.0.1:27017/";

const client = new MongoClient(dbUrl);
try {
    await client.connect();
    console.log("Mongo connected");
} catch (err) {
    console.error("Mongo connection failed:", err);
}
const db = client.db("spring-hotel");

export default  db