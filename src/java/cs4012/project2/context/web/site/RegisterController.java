/*
 * Tyler Filla
 * CS4012 - P2
 */

package cs4012.project2.context.web.site;

import cs4012.project2.context.web.site.service.AuthService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("register")
public class RegisterController {

    private static final Logger log = LogManager.getLogger();

    @Inject
    private AuthService mAuthService;

    @GetMapping
    public String get(Model model, HttpSession session) {
        // If logged in, redirect to profile page
        if (session.getAttribute("user") != null) {
            log.debug("Logged in, redirect to profile page");
            return "redirect:/profile";
        }

        // Set up registration attribute
        model.addAttribute("registration", new Registration());

        return "register";
    }

    @PostMapping("/new")
    public String post(@ModelAttribute Registration registration) {
        // Do the registration
        doRegistration(registration);

        // Redirect to login
        return "redirect:/login?registered=1";
    }

    /**
     * Carry out the registration process.
     * <p>
     * This is for demonstration purposes only (not for any secure use).
     *
     * @param registration The registration details
     */
    private void doRegistration(Registration registration) {
        log.debug("Registering user: " + registration.getUsername());

        // Perform the registration
        mAuthService.register(registration.getUsername(), registration.getPassword(), registration.getFname(),
                registration.getLname(), registration.getAddrBody(), registration.getAddrCity(),
                registration.getAddrState(), registration.getAddrZip());
    }

    @SuppressWarnings("WeakerAccess")
    public static class Registration {

        private String mUsername;
        private String mPassword;
        private String mFname;
        private String mLname;
        private String mAddrBody;
        private String mAddrCity;
        private String mAddrState;
        private String mAddrZip;

        public String getUsername() {
            return mUsername;
        }

        public void setUsername(String pUsername) {
            mUsername = pUsername;
        }

        public String getPassword() {
            return mPassword;
        }

        public void setPassword(String pPassword) {
            mPassword = pPassword;
        }

        public String getFname() {
            return mFname;
        }

        public void setFname(String pFname) {
            mFname = pFname;
        }

        public String getLname() {
            return mLname;
        }

        public void setLname(String pLname) {
            mLname = pLname;
        }

        public String getAddrBody() {
            return mAddrBody;
        }

        public void setAddrBody(String pAddrBody) {
            mAddrBody = pAddrBody;
        }

        public String getAddrCity() {
            return mAddrCity;
        }

        public void setAddrCity(String pAddrCity) {
            mAddrCity = pAddrCity;
        }

        public String getAddrState() {
            return mAddrState;
        }

        public void setAddrState(String pAddrState) {
            mAddrState = pAddrState;
        }

        public String getAddrZip() {
            return mAddrZip;
        }

        public void setAddrZip(String pAddrZip) {
            mAddrZip = pAddrZip;
        }

    }

}
