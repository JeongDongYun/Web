package exam;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class NewsVO{
	int id;
	String writer;
	String title;
	String regdate;
	String body;
	int readcnt;
}