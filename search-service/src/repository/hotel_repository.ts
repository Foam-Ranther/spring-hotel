import { Collection } from "mongodb";


export type Hotel = {
  "name": string;
  "roomsAvailable": number;
  "city": string;
};

export class HotelRepository {
  #collection;
  constructor(collection: Collection<Hotel>) {
    this.#collection = collection;
  }

  time(duration: number) {
    return new Promise((res) => setTimeout(() => res(1234), duration * 1000));
  }

  async searchHotel(city: string) {
    await this.time(3);
    const bookingList = await this.#collection.find({city}).toArray();
    return bookingList;
  }
}
