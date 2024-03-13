import java.util.Scanner;
public class maining {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        System.out.println("Enter number 1: ");
        MyNum num1 = new MyNum(s.nextLine());

        System.out.println("Enter number 2: ");
        MyNum num2 = new MyNum(s.nextLine());

        System.out.println("+ for add, - for subtract, * for multiply, / for division:");
        String op = s.nextLine();

        switch (op){
            case "+":
                System.out.println(add(ListVersion(num1.val()), ListVersion(num2.val())));
                break;
            case "-":
                if(isHigher(ListVersion(num1.val()), ListVersion(num2.val()))){
                    System.out.println(subtract(ListVersion(num1.val()), ListVersion(num2.val())));
                }else{
                    System.out.println("-" + subtract(ListVersion(num2.val()), ListVersion(num1.val())));
                }
                break;
            case "*":
                System.out.println(multiply(ListVersion(num1.val()), ListVersion(num2.val())));
                break;
            case "/":
                System.out.println(divide(ListVersionSpecial(num1.val()), ListVersionSpecial(num2.val())));
        }
        MyNum thousandzeros1 = new MyNum(zerogenerator(9,1000));
        MyNum thousandzeros2 = new MyNum(zerogenerator(7,1000));

        //System.out.println("Added thousand 9 and thousand 7 : " + add(ListVersion(bignum1.val()), ListVersion(bignum2.val())));
        //System.out.println("Added 90000... and 70000... : " + add(ListVersion(thousandzeros1.val()), ListVersion(thousandzeros2.val())));

        //System.out.println("Multiplied a thousand 9 and thousand 7 : " + multiply(ListVersion(bignum1.val()), ListVersion(bignum2.val())));
        //System.out.println("Multiplied 90000... and 70000... : " + multiply(ListVersion(thousandzeros1.val()), ListVersion(thousandzeros2.val())));

    }

    /**
     * The method "ListVersion" simply takes a String, takes its characters one by one from right to left
     * and adds them as integers to a LinkedList. Then it returns the list.
     * An important point is that it is specifically designed for strings with integer digits only.
     * Otherwise, you will get unwanted results.
     */
    public static LinkedList ListVersion(String s){
        LinkedList lis = new LinkedList();
        for(int i= s.length()-1; i>=0; i--){
            lis.insertLast(new Node(s.charAt(i)-'0'));
        }
        return lis;
    }

    /**
     * The method "ListVersion" simply takes a String, takes its characters one by one from right to left
     * and adds them as integers to a LinkedList. Then it returns the list.
     * An important point is that it is specifically designed for strings with integer digits only.
     * Otherwise, you will get unwanted results.
     */
    public static LinkedList ListVersionSpecial(String s){
        LinkedList lis = new LinkedList();
        for(int i=0; i < s.length(); i++){
            lis.insertLast(new Node(s.charAt(i)-'0'));
        }
        return lis;
    }

    public static boolean isHigher(LinkedList l1, LinkedList l2){
        Node temp1 = l1.getHead();
        Node temp2 = l2.getHead();
        String higher = "l1";

        while(temp1 != null || temp2 != null){
            if(temp1 == null && temp2 != null){
                return false;
            }
            else if(temp1 != null && temp2 == null){
                return true;
            }
            if(temp1.getData() > temp2.getData()){
                higher = "l1";
            }else if(temp1.getData() < temp2.getData()){
                higher = "l2";
            }
            temp1 = temp1.getNext();
            temp2 = temp2.getNext();
        }
         if(higher.equals("l1")){
            return true;
         }
        return false;
    }

    /**
     * The method "numbergenerator" allows the user to create numbers with large amounts of digits.
     * It takes which number you want the digits to be and how many digits you want the number to have.
     * Then it returns the number as a String.
     */
    public static String numbergenerator(int num, int times){
        String result = "";
        for(int i=0; i<times; i++){
            result += num;
        }
        return result;
    }

    /**
     The method "zerogenerator" allows the user to create numbers with large amounts of zeros.
     * It takes which number you want the number to have as the first digit and how many zeros
     * you want the number to have. Then it returns the number as a String.
     */
    public static String zerogenerator(int num, int times){
        String result = "" + num;
        for(int i=0; i<times; i++){
            result += 0;
        }
        return result;
    }

     /**
     * The method "add" takes two Linkedlists with a digit at each node
     * It considers them as two large numbers and do an addition
     * Then it returns the result as a Linkedlist with a digit at each node.
     */

    public static LinkedList add(LinkedList l1, LinkedList l2){
        LinkedList result = new LinkedList();
        Node n1 = l1.getHead();
        Node n2 = l2.getHead();
        int extra = 0;
        int val;
        while(n1 != null || n2 != null || extra != 0){
            val = extra;
            if(n1 != null){
                val += n1.getData();
                n1 = n1.getNext();
            }
            if(n2 != null){
                val += n2.getData();
                n2 = n2.getNext();
            }
            if(val >= 10){
                extra = 1;
                val -= 10;
            }
            else{
                extra = 0;
            }
            result.insertFirst(new Node(val));
        }
        return result;
    }

    /**
     * The method "subtract" takes two Linkedlists with a digit at each node
     * It considers them as two large numbers and do a subtraction
     * Then it returns the result as a Linkedlist with a digit at each node.
     * It does not work unless the first list symbolises a larger integer than the second one.
     */
    public static LinkedList subtract(LinkedList l1, LinkedList l2){
        LinkedList result = new LinkedList();
        Node n1 = l1.getHead();
        Node n2 = l2.getHead();
        int extra = 0;
        int val;
        while(n1 != null || extra != 0){
            val = extra;
            val += n1.getData();
            n1 = n1.getNext();
            if(n2 != null){
                val += (-1)*n2.getData();
                n2 = n2.getNext();
            }
            if(val < 0){
                extra = -1;
                val += 10;
            }
            else{
                extra = 0;
            }
            result.insertFirst(new Node(val));
        }
        while(result.getHead().getData() == 0){
            result.deleteFirst();
        }
        return result;
    }

    /**
     * The method "multiply" takes two Linkedlists with a digit at each node
     * It considers them as two large numbers and do a multiplication
     * Then it returns the result as a Linkedlist with a digit at each node.
     */
    public static LinkedList multiply(LinkedList l1, LinkedList l2){
        LinkedList result = new LinkedList();
        LinkedList mainresult = new LinkedList();
        Node n1 = l1.getHead();
        Node n2 = l2.getHead();
        int extra = 0;
        int val;
        long numofswipes = 0;
        long holder;

        while(n2 != null){

            while(n1 != null || extra != 0){
                val = extra;
                if(n1 != null){
                    val += n1.getData()*n2.getData();
                    n1 = n1.getNext();
                }
                if(val >= 10){
                    extra = val/10;
                    val %= 10;
                }
                else{
                    extra = 0;
                }
                result.insertLast(new Node(val));
            }

            holder = numofswipes;
            while(numofswipes != 0){
                result.insertFirst(new Node(0));
                numofswipes--;
            }
            numofswipes += (holder+1);

            if(n2.getNext() == null){
                mainresult = add(mainresult, result);
            }
            else{
                mainresult = ListVersion(add(mainresult, result).toString());
                result = new LinkedList();
            }
            n1 = l1.getHead();
            n2 = n2.getNext();
        }
        return mainresult;
    }

    /**
     * The method "divide" takes two Linkedlists with a digit at each node
     * It considers them as two large numbers and do a division
     * Then it returns the result as a Linkedlist with a digit at each node.
     * The second Linkedlist must contain only one element.
     * Otherwise, only its first node will be used.
     */
    public static LinkedList divide(LinkedList l1, LinkedList l2){
        Node n1 = l1.getHead();
        Node n2 = l2.getHead();
        int val = 0;
        int res = 0;
        LinkedList result = new LinkedList();

        while(n1 != null){
            val += n1.getData();
            if(val < n2.getData()){
                result.insertLast(new Node(0));
                val *= 10;
                n1 = n1.getNext();
            }else{
                while(val >= n2.getData()){
                    val -= n2.getData();
                    res ++;
                }
                result.insertLast(new Node(res));
                res = 0;
                val *= 10;
                n1 = n1.getNext();
            }
        }
        if(result.getHead().getData() == 0 && result.numberOfElements() != 1){
            result.deleteFirst();
        }
        return result;
    }
}
