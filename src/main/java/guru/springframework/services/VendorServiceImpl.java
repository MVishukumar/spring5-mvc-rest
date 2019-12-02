package guru.springframework.services;

import guru.springframework.api.v1.mapper.VendorMapper;
import guru.springframework.api.v1.model.VendorDTO;
import guru.springframework.domain.Vendor;
import guru.springframework.repositories.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {
    private final VendorRepository vendorRepository;
    private final VendorMapper vendorMapper;

    public VendorServiceImpl(VendorRepository vendorRepository, VendorMapper vendorMapper) {
        this.vendorRepository = vendorRepository;
        this.vendorMapper = vendorMapper;
    }

    @Override
    public List<VendorDTO> listAllVendors() {
        List<Vendor> vendors = vendorRepository.findAll();
        return vendors
                .stream()
                .map(vendor -> {
                    return vendorMapper.vendorToVendorDTO(vendor);
                })
                .collect(Collectors.toList());
    }

    @Override
    public VendorDTO findVendorById(Long id) {
        Optional<Vendor> vendor = vendorRepository.findById(id);
        if(vendor.isPresent()) {
            return vendorMapper.vendorToVendorDTO(vendor.get());
        } else {
            throw new ResourceNotFoundException();
        }

    }

    @Override
    public VendorDTO createNewVendor(VendorDTO vendorDTO) {
        Vendor vendorSaved = vendorRepository.save(vendorMapper.vendorDTOtoVendor(vendorDTO));

        return vendorMapper.vendorToVendorDTO(vendorSaved);
    }

    @Override
    public VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO) {
        Vendor vendor = vendorMapper.vendorDTOtoVendor(vendorDTO);
        vendor.setId(id);

        Vendor vendorSaved = vendorRepository.save(vendor);

        return vendorMapper.vendorToVendorDTO(vendorSaved);
    }

    @Override
    public VendorDTO patchVendor(Long id, VendorDTO vendorDTO) {
        //Find the vendor by id.
        //Populate vendor with all non null attributes from vendorDTO
        //Return vendor by converting it into VendorDTO
        return vendorRepository.findById(id)
                .map(vendor -> {
                    if(vendorDTO.getName() != null) {
                        vendor.setName(vendorDTO.getName());
                    }

                    return vendorMapper.vendorToVendorDTO(vendor);
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteVendor(Long id) {
        vendorRepository.deleteById(id);
    }
}
