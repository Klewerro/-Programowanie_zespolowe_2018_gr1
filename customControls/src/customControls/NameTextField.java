package customControls;

import com.sun.xml.internal.ws.util.StringUtils;
import javafx.scene.control.TextField;

public class NameTextField extends TextField implements ValidatedTextField{


    @Override
    public void replaceText(int start, int end, String text) {
        if (text.matches("[a-zA-ZąćęłńóśźżĄĘŁŃÓŚŹŻ]") || text.isEmpty()) {
            super.replaceText(start, end, text);
        }
    }


    @Override
    public String getTextValidated() throws CustomControlsException {
        String result = getText().toLowerCase();
        result = StringUtils.capitalize(result);
        if (result.length() < 2) {
            throw new CustomControlsException("Name must have at least two characters!");
        }

        return result;
    }
}
