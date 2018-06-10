package DbInterface;
import java.util.UUID;
public class IDGenrator {
	public static Integer GenId(){
        UUID idOne = UUID.randomUUID();
   
        String str=""+idOne;        
        int uid=str.hashCode();
        String filterStr=""+uid;
        str=filterStr.replaceAll("-", "");
        if(str.length()>10){
        return Integer.parseInt(str.substring(0, 10));}
        else
        	return Integer.parseInt(str);
	}
}
