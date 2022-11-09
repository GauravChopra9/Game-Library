
public class AEGame implements IGame, Comparable<AEGame>{
  String name, publisher, year, genre;
  
  @Override
  public int compareTo(AEGame o) {
    int compare = this.name.compareTo(o.name);
    if (compare == 0)
      compare = this.year.compareTo(o.year);
    if (compare == 0)
      compare = this.publisher.compareTo(o.year);
    return compare;
  }
  
  public AEGame(String name, String publisher, String year, String genre) {
    this.name = name;
    this.publisher = publisher;
    this.year = year;
    this.genre = genre;
  }
  
  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public String getPublisher() {
    // TODO Auto-generated method stub
    return this.publisher;
  }

  @Override
  public String getYear() {
    // TODO Auto-generated method stub
    return this.year;
  }

  @Override
  public String getGenre() {
    // TODO Auto-generated method stub
    return this.genre;
  }

}
