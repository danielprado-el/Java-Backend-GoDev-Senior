package GoDev.Backend.controller;

import GoDev.Backend.model.Item;
import GoDev.Backend.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/items")
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable(value = "id") UUID itemId) {
        Item item = itemRepository.findById(itemId);
        return ResponseEntity.ok().body(item);
    }

    @PostMapping("/items")
    public Item createItem(@Valid @RequestBody Item item) {
        return itemRepository.save(item);
    }

    @PutMapping("/items/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable(value = "id") UUID itemId, @Valid @RequestBody Item itemDetails) {
        Item item = itemRepository.findById(itemId);
        item.setDescription(itemDetails.getDescription());
        item.setValue(itemDetails.getValue());
        item.setType(itemDetails.getType());
        final Item updatedItem = itemRepository.save(item);
        return ResponseEntity.ok(updatedItem);
    }

    @DeleteMapping("/items/{id}")
    public void deleteItem(@PathVariable(value = "id") UUID itemId) {
        Item item = itemRepository.findById(itemId);
        itemRepository.delete(item);
    }
}
