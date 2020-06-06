package com.uabc.edu.apprest.web;

import com.uabc.edu.apprest.model.Cajero;
import com.uabc.edu.apprest.service.CajeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import com.uabc.edu.apprest.model.Articulo;
import com.uabc.edu.apprest.service.ArticuloService;

@RestController
@RequestMapping("/")
public class ProductoController {

    @Autowired
    private ArticuloService articuloService;
    private CajeroService cajeroService;

    @RequestMapping
    public String getArticulos(Model model) {
        List<Articulo> list = articuloService.getArticulos();
        model.addAttribute( "articulos",list);
        return "Tiendita";
     }
    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String editArticuloById(Model model, @PathVariable("id") Optional<Long> id)
    {
        if (id.isPresent()) {
            Articulo entity = articuloService.getArticuloById(id.get());
            model.addAttribute("articulo", entity);
        } else {
            model.addAttribute("articulo", new Articulo());
        }
        return "Tiendita";

    }
    @RequestMapping(path = "/delete/{id}")
    public String deleArticuloById(Model model, @PathVariable("id") Long id)

    {
        articuloService.deleArticuloById(id);
        return "redirect:/";
    }

    @RequestMapping(path = "/createArticulo", method = RequestMethod.POST)
    public String createOrUpdateArticulo(Articulo articulo)
    {
        articuloService.createOrUpdateArticulo(articulo);
        return "redirect:/";
    }

///cajero
    @ResponseBody
    public ResponseEntity<List> getAllEmployes() {

    return new ResponseEntity<List>(cajeroService.getAllEmployees(), HttpStatus.OK);
}

    @GetMapping("cajero/get/{id}")
    @ResponseBody
    public ResponseEntity<Optional<Cajero>> getCajeroByIds(@PathVariable("id") Long id) {

        return new ResponseEntity<Optional<Cajero>>(cajeroService.getCajeroById(id), HttpStatus.OK);
    }

}

