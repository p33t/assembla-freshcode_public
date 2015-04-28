package biz.freshcode.learn.gwt2.mod2.client.spike.gridgraphic;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import java.util.Map;

import static biz.freshcode.learn.gwt2.common.client.util.AppCollectionUtil.newMap;

/**
 * Renders a gannt-style table for a GgRow for a given range.
 */
public class GgCell extends AbstractCell<GgRow> {
    private int from;
    private int toEx;

    GgCell(int from, int toEx) {
        this.from = from;
        this.toEx = toEx;
    }

    public SafeHtml linesHeader() {
        SafeHtmlBuilder sb = new SafeHtmlBuilder();
//         border-top: none; border-bottom: none; border-collapse:collapse;
        sb.appendHtmlConstant("<table style='border-collapse:collapse; width:100%;'><tr>");
        for (int i = from; i < toEx; i++) {
            renderCell(sb, percent(1), "+" + i, "inherit", false);
//            sb.appendHtmlConstant("<td style='width:" + percent(1) + "%; text-align:center; border:1px solid gray;'>");
//            sb.appendEscaped("+" + i);
//            sb.appendHtmlConstant("</td>");
        }
        sb.appendHtmlConstant("</tr></table>");
        return sb.toSafeHtml();
    }

    @Override
    public void render(Context context, GgRow row, SafeHtmlBuilder sb) {
        // NOTE: Elems assumed to be in order
        int elemCount = row.getElems().size();
        Map<String, Integer> widths = newMap();
        for (int i = 0; i < elemCount; i++) {
            GgElem current = row.getElems().get(i);
            GgElem nextOrNull = i + 1 < elemCount ? row.getElems().get(i + 1) : null;
            int width = width(current, nextOrNull);
            if (width > 0) widths.put(current.getId(), width);
        }

        if (widths.size() > 0) {
            int lastToEx = 0;
            sb.appendHtmlConstant("<table style='border:none; border-collapse:collapse; width:100%;'><tr>");
            for (GgElem e : row.getElems()) {
                Integer w = widths.get(e.getId());
                if (w != null) {
                    int from = truncate(e.getFromIn());
                    int space = percent(from - lastToEx);
                    lastToEx = from + w;
                    if (space > 0) renderCell(sb, space, " ", "inherit", true);
                    renderCell(sb, percent(w), e.getTitle(), e.getColour(), true);
                }
            }
            int rem = percent(toEx - lastToEx);
            if (rem > 0) renderCell(sb, rem, " ", "inherit", true);

            sb.appendHtmlConstant("</tr/></table>");
        }
    }

    private int percent(int width) {
        int total = toEx - from;
        return Math.round(100f * width / total);
    }

    private void renderCell(SafeHtmlBuilder sb, int perc, String title, String colour, boolean pad) {
        String padding = pad ? " padding:4px 0px;" : "";
        String line = pad? "solid": "dotted";
        sb.appendHtmlConstant("<td style='width:" + perc + "%; border-right: 1px " + line + " lightgray; background-color:" + colour +
                "; text-align:center;" + padding + "'>");
        sb.appendEscaped(title);
        sb.appendHtmlConstant("</td>");
    }

    private int width(GgElem elem, GgElem nextOrNull) {
        int from = truncate(elem.getFromIn());
        int toEx = truncate(elem.getToEx());
        if (nextOrNull != null) {
            // next elem will truncate
            toEx = Math.min(toEx, nextOrNull.getFromIn());
        }
        return Math.max(0, toEx - from);
    }

    private int truncate(int i) {
        int end = Math.min(toEx, i);
        return Math.max(from, end);
    }
}
