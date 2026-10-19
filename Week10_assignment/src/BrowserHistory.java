
class PageNode {
    String url;
    PageNode prev, next;

    PageNode(String url) {
        this.url = url;
        prev = null;
        next = null;
    }
}

public class BrowserHistory {
    PageNode current;

    BrowserHistory(String homepage) {
        current = new PageNode(homepage);
    }

    void visit(String url) {
        PageNode newPage = new PageNode(url);

        current.next = null;

        newPage.prev = current;
        current.next = newPage;

        current = newPage;
    }

    String back(int steps) {
        while (steps > 0 && current.prev != null) {
            current = current.prev;
            steps--;
        }

        return current.url;
    }

    String forward(int steps) {
        while (steps > 0 && current.next != null) {
            current = current.next;
            steps--;
        }

        return current.url;
    }

    public static void main(String[] args) {
        BrowserHistory browser = new BrowserHistory("srm.edu");

        browser.visit("a.com");
        browser.visit("b.com");
        browser.visit("c.com");

        System.out.println(browser.back(1));
        System.out.println(browser.back(1));
        System.out.println(browser.forward(1));

        browser.visit("d.com");

        System.out.println(browser.forward(2));
        System.out.println(browser.back(2));
        System.out.println(browser.back(7));
    }
}

