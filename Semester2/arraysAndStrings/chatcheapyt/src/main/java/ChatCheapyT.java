import java.util.Scanner;

public class ChatCheapyT{

  private static String handleSilence(String input){
    if (input.isBlank())
      return "Dann sage ich aber auch nichts!";
    else
      return null;
  }

  private static String handleTooLong(String input){

    // TODO
    return null;

  }

  private static String handleExam(String input){

    // TODO
    return null;

  }

  private static String handleQuestion(String input){

    // TODO
    return null;

  }

  private static String handleExclamation(String input){

    // TODO
    return null;

  }

  private static String handleChatGPT(String input){

    // TODO
    return null;

  }

  private static String handleScream(String input){

    // TODO
    return null;

  }

  private static String handleReverse(String input){

    // TODO
    return null;

  }

  private static String handleAdd(String input){

    // TODO
    return null;

  }

  public static void main(String[] args) {

    Scanner input = new Scanner(System.in);

    String prompt = null;

    do {

      System.out.print("Prompt: ");
      prompt = input.nextLine(); 

      String answer = handleSilence(prompt);

      if (answer == null)
        answer = handleTooLong(prompt);

      if (answer == null)
        answer = handleExam(prompt);

      if (answer == null)
        answer = handleQuestion(prompt);

      if (answer == null)
        answer = handleExclamation(prompt);

      if (answer == null)
        answer = handleScream(prompt);

      if (answer == null)
        answer = handleReverse(prompt);

      if (answer == null)
        answer = handleAdd(prompt);

      if (answer == null)
        answer = handleChatGPT(prompt);

      if (prompt.equalsIgnoreCase("bye"))
        continue;

      if (answer != null)        
        System.out.println("ChatCheapyT: " + answer);
      else
        System.out.println("ChatCheapyT: Ich verstehe Sie leider nicht!");


    } while (!prompt.equalsIgnoreCase("bye"));

    System.out.println("Bye!");

    input.close();
    
  }





}
