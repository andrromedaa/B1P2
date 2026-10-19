
class TicketNode {
    int data;
    TicketNode next;

    TicketNode(int data) {
        this.data = data;
        next = null;
    }
}

public class MergingTickets {

    static TicketNode mergeSorted(TicketNode a, TicketNode b) {
        TicketNode dummy = new TicketNode(0);
        TicketNode tail = dummy;

        while (a != null && b != null) {
            if (a.data <= b.data) {
                tail.next = a;
                a = a.next;
            } else {
                tail.next = b;
                b = b.next;
            }

            tail = tail.next;
        }

        if (a != null)
            tail.next = a;

        if (b != null)
            tail.next = b;

        return dummy.next;
    }

    static void display(TicketNode head) {
        TicketNode temp = head;

        while (temp != null) {
            System.out.print(temp.data);

            if (temp.next != null)
                System.out.print(" -> ");

            temp = temp.next;
        }

        System.out.println();
    }

    public static void main(String[] args) {
        TicketNode a = new TicketNode(3);
        a.next = new TicketNode(8);
        a.next.next = new TicketNode(15);

        TicketNode b = new TicketNode(5);
        b.next = new TicketNode(8);
        b.next.next = new TicketNode(12);
        b.next.next.next = new TicketNode(20);

        TicketNode result = mergeSorted(a, b);

        display(result);
    }
}
