package edu.uci.inf122.guildquest.ui;

import edu.uci.inf122.guildquest.content.Realm;
import edu.uci.inf122.guildquest.content.RealmFactory;
import edu.uci.inf122.guildquest.content.TimeRule;

public class RealmUI {
    static private final Page page = Page.getPage();

    public static Realm createRealm() {
        String name = page.acceptStrWithValidation("Please name this realm:\n");
        String desc = page.acceptStrWithValidation("Please describe this realm:\n");
        int offset = page.acceptIntUntil("Please choose a time offset:", Integer.MAX_VALUE);
        return RealmFactory.createRealm(name, desc, new TimeRule(offset, 1.0), 10, 10);
    }
}
