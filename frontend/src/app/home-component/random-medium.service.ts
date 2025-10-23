export class RandomMediumService {

  constructor(private medien : { [key: string]: any[] }) {}

  getRandomMedium() : any {
    const categories = Object.keys(this.medien);
    if (categories.length === 0) {
      return null;
    }
    const randomCategory = categories[Math.floor(Math.random() * categories.length)];
    const itemsInCategory = this.medien[randomCategory];
    if (itemsInCategory.length === 0) {
      return null;
    }
    const randomItem = itemsInCategory[Math.floor(Math.random() * itemsInCategory.length)];
    return randomItem;
  }

}
