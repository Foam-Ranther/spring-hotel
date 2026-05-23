import { Collection } from "mongodb";

export type Booking = {
  "userId": string;
  "roomId": string;
  "rooms": number;
};

export class BookingRepository {
  #collection;
  constructor(collection: Collection<Booking>) {
    this.#collection = collection;
  }

  async bookHotel(username:string, { hotel_id, rooms }: {hotel_id: string, rooms: number}) {
    const bookedHotel = {
      "userId": username,
      "roomId": hotel_id,
      "rooms": rooms,
    };
    return await this.#collection.insertOne(bookedHotel);
  }
  async listBookings(username: string) {
    const bookingList = await this.#collection.find({userId: username}).toArray();
    return bookingList
  }

  time(duration: number) {
    return new Promise((res)=> setTimeout(() =>res(1234), duration * 1000))
  }

  async searchHotel(city: string) {
    await this.time(3); 
    // const bookingList = await this.#collection.find({userId: username}).toArray();
    return ['Hotel Plaza', 'Hotel Ritz', 'Hotel Marriott'];
  }

}
