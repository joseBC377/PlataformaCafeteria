package com.example.demo.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.*;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entities.Productos;
import com.example.demo.entities.SubCategoria;
import com.example.demo.services.ProductosService;
import com.example.demo.services.SubCategoriaService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "api/productos", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ProductosController {

    private final ProductosService service;
    private final SubCategoriaService subCategoriaService;

    @GetMapping("lista")
    public List<Productos> selectAll() {
        return service.selectAll();
    }

    @GetMapping("lista/{id}")
    public Productos selectProductosById(@PathVariable Integer id) {
        return service.selectId(id);
    }

    // ✅ Nuevo método para insertar con archivo
    @PostMapping(value = "insertar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> insertarProducto(
            @RequestParam("nombre") String nombre,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("precio") BigDecimal precio,
            @RequestParam("id_subcategoria") Integer idSubcategoria,
            @RequestParam("imagen") MultipartFile imagenFile) {

        try {
            SubCategoria subcategoria = subCategoriaService.selectId(idSubcategoria);

            String fileName = System.currentTimeMillis() + "_" + imagenFile.getOriginalFilename();
            String uploadDir = "uploads/";
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(imagenFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            Productos producto = new Productos();
            producto.setNombre(nombre);
            producto.setDescripcion(descripcion);
            producto.setPrecio(precio);
            producto.setImagen("/uploads/" + fileName);
            producto.setSubcategoria(subcategoria);

            return ResponseEntity.ok(service.insert(producto));

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar imagen");
        }
    }

    // ✅ Nuevo método para actualizar con archivo
    @PutMapping(value = "actualizar/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> actualizarProducto(
            @PathVariable Integer id,
            @RequestParam("nombre") String nombre,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("precio") BigDecimal precio,
            @RequestParam("id_subcategoria") Integer idSubcategoria,
            @RequestParam(value = "imagen", required = false) MultipartFile imagenFile) {

        try {
            Productos producto = service.selectId(id);
            SubCategoria subcategoria = subCategoriaService.selectId(idSubcategoria);

            producto.setNombre(nombre);
            producto.setDescripcion(descripcion);
            producto.setPrecio(precio);
            producto.setSubcategoria(subcategoria);

            if (imagenFile != null && !imagenFile.isEmpty()) {
                String fileName = System.currentTimeMillis() + "_" + imagenFile.getOriginalFilename();
                String uploadDir = "uploads/";
                Path uploadPath = Paths.get(uploadDir);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(imagenFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                producto.setImagen("/uploads/" + fileName);
            }

            return ResponseEntity.ok(service.updateProductos(id, producto));

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar producto");
        }
    }

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<String> deleteProductos(@PathVariable Integer id) {
        service.deleteProductos(id);
        return ResponseEntity.ok("Producto eliminado correctamente. ID: " + id);
    }
}
