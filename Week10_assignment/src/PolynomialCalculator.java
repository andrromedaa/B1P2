class PolyNode {
    int coeff;
    int exp;
    PolyNode next;

    PolyNode(int coeff, int exp) {
        this.coeff = coeff;
        this.exp = exp;
        next = null;
    }
}

public class PolynomialCalculator {

    static PolyNode addPolynomials(PolyNode p, PolyNode q) {
        PolyNode dummy = new PolyNode(0, 0);
        PolyNode tail = dummy;

        while (p != null && q != null) {
            if (p.exp > q.exp) {
                tail.next = new PolyNode(p.coeff, p.exp);
                p = p.next;
            } else if (q.exp > p.exp) {
                tail.next = new PolyNode(q.coeff, q.exp);
                q = q.next;
            } else {
                int sum = p.coeff + q.coeff;

                if (sum != 0)
                    tail.next = new PolyNode(sum, p.exp);

                if (sum != 0)
                    tail = tail.next;

                p = p.next;
                q = q.next;
            }

            if (tail.next != null)
                tail = tail.next;
        }

        while (p != null) {
            tail.next = new PolyNode(p.coeff, p.exp);
            tail = tail.next;
            p = p.next;
        }

        while (q != null) {
            tail.next = new PolyNode(q.coeff, q.exp);
            tail = tail.next;
            q = q.next;
        }

        return dummy.next;
    }

    static void display(PolyNode head) {
        if (head == null) {
            System.out.println("0");
            return;
        }

        PolyNode temp = head;
        boolean first = true;

        while (temp != null) {
            int c = temp.coeff;
            int e = temp.exp;

            if (!first) {
                if (c > 0)
                    System.out.print(" + ");
                else
                    System.out.print(" - ");
            } else if (c < 0) {
                System.out.print("-");
            }

            int absCoeff = Math.abs(c);

            if (e == 0 || absCoeff != 1)
                System.out.print(absCoeff);

            if (e > 0)
                System.out.print("x");

            if (e > 1)
                System.out.print("^" + e);

            first = false;
            temp = temp.next;
        }

        System.out.println();
    }

    public static void main(String[] args) {
        PolyNode p = new PolyNode(5, 3);
        p.next = new PolyNode(4, 2);
        p.next.next = new PolyNode(2, 0);

        PolyNode q = new PolyNode(5, 2);
        q.next = new PolyNode(-3, 1);
        q.next.next = new PolyNode(1, 0);

        PolyNode result = addPolynomials(p, q);

        display(result);
    }
}
