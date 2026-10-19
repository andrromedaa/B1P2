
class TrainNode {
    int data;
    TrainNode next;

    TrainNode(int data) {
        this.data = data;
        next = null;
    }
}

public class PalindromeTrain {

    static TrainNode reverse(TrainNode head) {
        TrainNode prev = null;
        TrainNode current = head;

        while (current != null) {
            TrainNode nextNode = current.next;
            current.next = prev;
            prev = current;
            current = nextNode;
        }

        return prev;
    }

    static boolean isPalindrome(TrainNode head) {
        if (head == null || head.next == null)
            return true;

        TrainNode slow = head;
        TrainNode fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        TrainNode secondHalf = reverse(slow.next);
        TrainNode first = head;
        TrainNode second = secondHalf;

        boolean result = true;

        while (second != null) {
            if (first.data != second.data) {
                result = false;
                break;
            }

            first = first.next;
            second = second.next;
        }

        slow.next = reverse(secondHalf);

        return result;
    }

    static void display(TrainNode head) {
        TrainNode temp = head;

        while (temp != null) {
            System.out.print(temp.data);

            if (temp.next != null)
                System.out.print(" -> ");

            temp = temp.next;
        }

        System.out.println();
    }

    public static void main(String[] args) {
        TrainNode head = new TrainNode(1);
        head.next = new TrainNode(2);
        head.next.next = new TrainNode(3);
        head.next.next.next = new TrainNode(2);
        head.next.next.next.next = new TrainNode(1);

        System.out.println(isPalindrome(head));
        display(head);
    }
}
