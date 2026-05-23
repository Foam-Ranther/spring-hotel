import {MongoClient} from "mongodb";

const dbUrl:string = Deno.env.get("MONGO_DB_URL")! || "mongodb://localhost:27017/spring-hotel"; 

const client = new MongoClient(dbUrl);
await client.connect( );

const db = client.db("postsDb");

export default  db