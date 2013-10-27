import java.util.*;

public interface Instruction {

	void decode(String code);
	void run(HashMap<String,Integer> variables);

}
