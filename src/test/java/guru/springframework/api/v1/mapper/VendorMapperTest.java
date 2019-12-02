package guru.springframework.api.v1.mapper;

import guru.springframework.api.v1.model.VendorDTO;
import guru.springframework.domain.Vendor;
import org.junit.Test;

import static org.junit.Assert.*;

public class VendorMapperTest {
    private static final String name = "Vendor1";
    private static final Long id = 1L;

    VendorMapper vendorMapper = VendorMapper.INSTANCE;

    @Test
    public void vendorDTOtoVendor() {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(name);
        vendorDTO.setId(id);

        Vendor vendor = vendorMapper.vendorDTOtoVendor(vendorDTO);

        assertEquals(vendor.getName(), vendorDTO.getName());
        assertEquals(vendor.getId(), vendorDTO.getId());
    }

    @Test
    public void vendorToVendorDTO() {
        Vendor vendor = new Vendor();
        vendor.setName(name);
        vendor.setId(id);

        VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);

        assertEquals(vendor.getName(), vendorDTO.getName());
        assertEquals(vendor.getId(), vendorDTO.getId());
    }
}