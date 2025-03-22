package com.example.dio.controller;

import com.example.dio.dto.request.RegistrationRequest;
import com.example.dio.dto.request.UserRequest;
import com.example.dio.dto.response.UserResponse;
import com.example.dio.exception.handler.UserExceptionHandler;
import com.example.dio.exception.handler.UserNotFoundByIdException;
import com.example.dio.security.util.UserIdentity;
import com.example.dio.service.UserService;
import com.example.dio.util.FieldErrorResponse;
import com.example.dio.util.ResponseBuilder;
import com.example.dio.util.ResponseStructure;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jdk.jshell.spi.ExecutionControl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Tag(name = "User Controller",description = "This is a Collection of API endpoints")
@RequestMapping("${app.base-url}")
public class UserController {
    private final UserService userService;


    //User registration API
    @Operation(description = """
            This API end point is used to register user.
            The end point requires specific user role along with other details.
            """,
    responses = {
            @ApiResponse(responseCode = "201",description = "User Created"),
            @ApiResponse(responseCode = "400",description = "Invalid Input",content = {
                    @Content(schema = @Schema(implementation = FieldErrorResponse.class))
            })
    })
    @PostMapping("/register")
    public ResponseEntity<ResponseStructure<UserResponse>> registerUser(@RequestBody @Valid RegistrationRequest registrationRequest){
        UserResponse userResponse=userService.registerUser(registrationRequest);
        return ResponseBuilder.created("User Created", userResponse);

    }

    //Find User API
    @Operation(description = """
            This API end point is used to find particular user.
            The end point requires specific user id.
            """,
            responses = {
                    @ApiResponse(responseCode = "200", description = "Found Successfully"),
                    @ApiResponse(responseCode = "404", description = "Not Found By Id", content = {
                            @Content(schema = @Schema(implementation = FieldErrorResponse.class))
                    }),
                    @ApiResponse(responseCode = "400",description = "Bad Request-Missing required fields"),
            })
    @GetMapping("/account")
    public ResponseEntity<ResponseStructure<UserResponse>> findUserByEmail(){
        UserResponse userResponse=userService.findUserByEmail();
        return ResponseBuilder.ok("User Found", userResponse);
    }


    //Update User API
    @Operation(description = """
            This API end point is used to update user details.
            The end point requires specific user id and other details.
            """,responses = {
            @ApiResponse(responseCode = "200", description = "Found Successfully"),
            @ApiResponse(responseCode = "404", description = "Not Found By Id, Update Failure", content = {
                    @Content(schema = @Schema(implementation = FieldErrorResponse.class))
            })
    })
    @PutMapping("/users")
    public ResponseEntity<ResponseStructure<UserResponse>> updateUserByEmail(@RequestBody @Valid UserRequest userRequest){
        UserResponse userResponse=userService.updateUserByEmail(userRequest);
        return ResponseBuilder.ok("User Updated",userResponse);
    }

}
