package Backend.Account;

public class AccountError implements AccountErrors{
    public void display(int errorCode){
        // display the error message 
        switch(errorCode){
            case 404:
                System.out.println("Error 404: Page not found");
                break;
            case 500:
                System.out.println("Error 500: Internal Server Error");
                break;
            case 403:
                System.out.println("Error 403: Forbidden");
                break;
            case 401:
                System.out.println("Error 401: Unauthorized");
                break;
            default:
                System.out.println("Error: Unknown error");
        }
    }
}