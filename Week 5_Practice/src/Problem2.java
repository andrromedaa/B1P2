class AccessRuleEngine2 {

    static String classifyAccess(String fieldModifier,
                                 String accessorContext) {

        // private
        if (fieldModifier.equals("private")) {
            if (accessorContext.equals("SAME_CLASS"))
                return "ALLOWED";
            else
                return "DENIED";
        }

        // default
        if (fieldModifier.equals("default")) {
            if (accessorContext.equals("SAME_CLASS") ||
                    accessorContext.equals("SAME_PACKAGE"))
                return "ALLOWED";
            else
                return "DENIED";
        }

        // protected
        if (fieldModifier.equals("protected")) {

            if (accessorContext.equals("SAME_CLASS") ||
                    accessorContext.equals("SAME_PACKAGE") ||
                    accessorContext.equals("SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"))
                return "ALLOWED";
            else
                return "DENIED";
        }

        // public
        if (fieldModifier.equals("public")) {
            return "ALLOWED";
        }

        return "DENIED";
    }


    static String describeContext(String accessorContext) {

        String[] words = accessorContext.split("_");

        String result = "";

        for (int i = 0; i < words.length; i++) {

            String word = words[i];

            word = word.substring(0, 1)
                    + word.substring(1).toLowerCase();

            result = result + word + " ";
        }

        return result.trim();
    }
}


public class Problem2 {

    public static void main(String[] args) {

        System.out.println(
                AccessRuleEngine2.classifyAccess(
                        "protected",
                        "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"
                )
        );

        System.out.println(
                AccessRuleEngine2.classifyAccess(
                        "protected",
                        "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"
                )
        );

        System.out.println(
                AccessRuleEngine2.describeContext(
                        "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"
                )
        );
    }
}