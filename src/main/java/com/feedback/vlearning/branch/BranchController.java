package com.feedback.vlearning.branch;

import com.feedback.vlearning.configuration.SecurityUtility;
import com.feedback.vlearning.user.UserDTO;
import com.feedback.vlearning.user.UserError;
import com.feedback.vlearning.user.UserService;
import com.feedback.vlearning.user.UserValidation;
import com.feedback.vlearning.utility.ParameterConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/auth/admin")
public class BranchController {

    @Autowired
    BranchValidation branchValidation;

    @Autowired
    BranchService branchService;

    @Autowired
    UserValidation userValidation;

    @Autowired
    UserService userService;

    @Autowired
    SecurityUtility securityUtility;

    @GetMapping("/branch/add")
    public String getBranchList(Model model) {
        model.addAttribute(ParameterConstants.PARAM_ACTION,"add");
        return "branch/add";
    }

    @GetMapping("/branch/edit/{id}")
    public String editBranch(@PathVariable("id")Long id,Model model) {
        BranchDTO branchDTO=branchService.findBranchById(id);
        UserDTO userDTO=userService.findUserById(branchDTO.getUserId());
        userDTO.setPassword("");
        model.addAttribute(ParameterConstants.PARAM_ACTION,"edit");
        model.addAttribute(ParameterConstants.PARAM_BRANCH,branchDTO);
        model.addAttribute(ParameterConstants.PARAM_USER,userDTO);
        return "branch/add";
    }

    @PostMapping("/branch/add")
    public String saveBranch(RedirectAttributes redirectAttributes, BranchDTO branchDTO, UserDTO userDto){
        BranchError branchError=branchValidation.validateBranch(branchDTO,"add");
        UserError userError=userValidation.validateUser(userDto,"add");
        if (branchError.getValid()&&userError.getValid()){
            branchService.save(userDto,branchDTO);
            redirectAttributes.addFlashAttribute(ParameterConstants.PARAM_MESSAGE, "Success");
            redirectAttributes.addFlashAttribute(ParameterConstants.PARAM_TEXT, "Added Sucessfully.");
            return "redirect:/auth/admin/branch/list";
        }else {
            redirectAttributes.addFlashAttribute(ParameterConstants.PARAM_ERROR,branchError);
            redirectAttributes.addFlashAttribute(ParameterConstants.PARAM_USER_ERROR,userError);
            redirectAttributes.addFlashAttribute(ParameterConstants.PARAM_BRANCH,branchDTO);
            redirectAttributes.addFlashAttribute(ParameterConstants.PARAM_USER,userDto);
            redirectAttributes.addFlashAttribute(ParameterConstants.PARAM_VALID,"show");
            redirectAttributes.addFlashAttribute(ParameterConstants.PARAM_MESSAGE,"Branch not added!");
            return "redirect:/auth/admin/branch/add";
        }
    }

    @PostMapping("/branch/edit")
    public String editBranch(RedirectAttributes redirectAttribute, BranchDTO branchDTO,
                             UserDTO userDTO, @ModelAttribute("userId")Long uid){
        BranchError branchError=branchValidation.validateBranch(branchDTO,"edit");
        branchDTO.setUserId(uid);
        userDTO.setId(uid);
        UserError userError=userValidation.validateUser(userDTO,"edit");
        if (branchError.getValid()&&userError.getValid()) {
            branchService.edit(userDTO,branchDTO);
            redirectAttribute.addFlashAttribute(ParameterConstants.PARAM_MESSAGE, "Success");
            redirectAttribute.addFlashAttribute(ParameterConstants.PARAM_TEXT, "Edited Sucessfully.");
            return "redirect:/auth/admin/branch/list";
        }else {
            redirectAttribute.addFlashAttribute(ParameterConstants.PARAM_ERROR,branchError);
            redirectAttribute.addFlashAttribute(ParameterConstants.PARAM_USER_ERROR,userError);
            redirectAttribute.addFlashAttribute(ParameterConstants.PARAM_BRANCH,branchDTO);
            redirectAttribute.addFlashAttribute(ParameterConstants.PARAM_USER,userDTO);
            redirectAttribute.addFlashAttribute(ParameterConstants.PARAM_VALID,"show");
            redirectAttribute.addFlashAttribute(ParameterConstants.PARAM_MESSAGE,"Branch not edited!");
            return "redirect:/auth/admin/branch/edit/"+branchDTO.getId();
        }
    }

    @GetMapping("/branch/list")
    public String saveBranchList(Model model) {
        model.addAttribute(ParameterConstants.PARAM_BRANCH,branchService.findAll());
        return "branch/list";
    }

}
