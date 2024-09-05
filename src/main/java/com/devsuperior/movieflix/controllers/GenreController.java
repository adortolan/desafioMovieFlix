package com.devsuperior.movieflix.controllers;

import com.devsuperior.movieflix.dto.GenreDTO;
import com.devsuperior.movieflix.services.GenreServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/genres")
public class GenreController {
    @Autowired
    private GenreServices genreServices;

    @PreAuthorize("hasAnyRole('ROLE_VISITOR','ROLE_MEMBER')")
    @GetMapping
    public ResponseEntity<List<GenreDTO>> findAll() {
        List<GenreDTO> list = genreServices.findAll();
        return ResponseEntity.ok().body(list);
    }
}
