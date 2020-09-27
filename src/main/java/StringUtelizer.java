

public class StringUtelizer {
    private String str;

    public StringUtelizer(String str) {
        this.str = str;
    }

    public String reversify_string() {
        if (this.str == null || this.str.trim().length() < 1) {
            return "invalid input";
        }
        return new StringBuilder(this.str).reverse().toString();
    }

    public String uppercase_string() {
        if (this.str == null || this.str.trim().length() < 1) {
            return "invalid input";
        }
        return this.str.toUpperCase();
    }

    public String lowercase_string() {
        if (this.str == null || this.str.trim().length() < 1) {
            return "invalid input";
        }
        return this.str.toLowerCase();
    }
}
