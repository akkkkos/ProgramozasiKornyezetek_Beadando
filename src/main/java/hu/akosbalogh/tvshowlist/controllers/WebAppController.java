package hu.akosbalogh.tvshowlist.controllers;

import hu.akosbalogh.tvshowlist.data.model.TvShow;
import hu.akosbalogh.tvshowlist.data.model.User;
import hu.akosbalogh.tvshowlist.service.impl.TvShowService;
import hu.akosbalogh.tvshowlist.service.impl.UserService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

/**
 * Web Controller for serving the pages to the user.
 */
@Controller
@RequestMapping("/")
public class WebAppController {

    private final TvShowService tvShowService;
    private final UserService userService;

    @Autowired
    public WebAppController(TvShowService tvShowService, UserService userService) {
        this.tvShowService = tvShowService;
        this.userService = userService;
    }

    /**
     * Build the main landing page for the user.
     *
     * @param model Model.
     * @return Returns the user to the main page.
     */
    @GetMapping
    public String getMainPage(Model model) {
        if (!userService.retrieveAllUsers().isEmpty()) {
            model.addAttribute("users", userService.retrieveAllUsers().toArray());
        }
        return "index";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        return "register";
    }

    /**
     * Generate the user's page and return it.
     *
     * @param id User id.
     * @param model Model
     * @return Returns the user to its page.
     */
    @GetMapping("/user/{id}")
    public String getRegisterPage(@PathVariable Long id, Model model) {

        Optional<User> optionalUser = userService.retrieveUserById(id);

        if (tvShowService.retrieveAllTvShowsMatchingUserId(id) != null) {
            if (!tvShowService.retrieveAllTvShowsMatchingUserId(id).isEmpty()) {
                model.addAttribute("shows", tvShowService.retrieveAllTvShowsMatchingUserId(id)
                        .toArray());
            }
        }

        model.addAttribute("user", optionalUser.get());
        return "userpage";
    }

    /**
     * Get page for adding Shows to user's list.
     *
     * @param id User id.
     * @param model Model.
     * @return Returns the user to the add page.
     */
    @GetMapping("/add/{id}")
    public String getAddPage(@PathVariable Long id, Model model) {
        model.addAttribute("userId", id);
        return "addTvShow";
    }

    /**
     * Get page for editing Tv Show entries.
     *
     * @param userId User Id.
     * @param showId Show Id.
     * @param model Model.
     * @return Returns the edit page to the user.
     */
    @GetMapping("/edit/{userId}/{showId}")
    public String getEditPage(@PathVariable Long userId,
                              @PathVariable Long showId,
                              Model model) {
        model.addAttribute("userId", userId);
        model.addAttribute("showId", showId);

        Optional<TvShow> tvShow = tvShowService.retrieveTvShowById(showId);
        model.addAttribute("show", tvShow.get());
        return "editTvShow";
    }

    /**
     * Delete Show from user's list then return user back to list.
     *
     * @param userId User id.
     * @param showId TvShow id.
     * @param model Model.
     * @return Returns the user to its page.
     */
    @GetMapping("/delete/{userId}/{showId}")
    public RedirectView deleteShow(@PathVariable Long userId,
                                   @PathVariable Long showId,
                                   Model model) {

        tvShowService.deleteTvShowById(showId);

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/user/" + userId);
        return redirectView;
    }

    /**
     * Add Tv Show to user's list.
     *
     * @param id User id.
     * @param model Model.
     * @param tvShow The Tv Show data coming from the user.
     * @return Returns the user to their page.
     */
    @PostMapping("/add/{id}")
    public RedirectView addShowToList(@PathVariable Long id, Model model, TvShow tvShow) {
        tvShow.setUserId(id);
        tvShowService.createTvShow(tvShow);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/user/" + id);
        return redirectView;
    }

    /**
     * Update the Tv Show data with the user input.
     *
     * @param userId User Id.
     * @param showId Show Id.
     * @param model Model.
     * @param tvShow The user's data of the Tv Show.
     * @return Returns the user to its page.
     */
    @PostMapping("/edit/{userId}/{showId}")
    public RedirectView editShowData(@PathVariable Long userId,
                                     @PathVariable Long showId,
                                     Model model, TvShow tvShow) {
        tvShow.setUserId(userId);
        tvShow.setId(showId);
        tvShowService.updateTvShow(tvShow);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/user/" + userId);
        return redirectView;
    }

    /**
     * Create user with parameters from register page.
     *
     * @param model Model.
     * @param user User.
     * @return Returns the user to the main page.
     */
    @PostMapping("/register")
    public RedirectView registerUser(Model model, User user) {
        userService.createUser(user);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/");
        return redirectView;
    }

    /**
     * Log in the user to its own page.
     *
     * @param model Model.
     * @param user Login form input.
     * @return Returns the user's page or sends it back to the login page if user is not found.
     */
    @PostMapping("/login")
    public RedirectView loginUser(Model model, User user) {
        Optional<User> optionalUser = userService.retrieveUserByUserName(user.getUserName());
        RedirectView redirectView = new RedirectView();

        return optionalUser.map(song -> {
            model.addAttribute("user", optionalUser);
            User newUser = optionalUser.get();
            redirectView.setUrl("user/" + newUser.getId());
            return redirectView;
        }).orElseGet(() -> {
            redirectView.setUrl("/?bad=1");
            return redirectView;
        });
    }
}
