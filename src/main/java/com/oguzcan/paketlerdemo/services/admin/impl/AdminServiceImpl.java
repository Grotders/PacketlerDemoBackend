package com.oguzcan.paketlerdemo.services.admin.impl;

import com.oguzcan.paketlerdemo.core.utilities.results.*;
import com.oguzcan.paketlerdemo.dto.AdminDto;
import com.oguzcan.paketlerdemo.dto.CustomerDto;
import com.oguzcan.paketlerdemo.dto.PacketDto;
import com.oguzcan.paketlerdemo.entities.*;
import com.oguzcan.paketlerdemo.repository.AdminRepository;
import com.oguzcan.paketlerdemo.repository.CustomerRepository;
import com.oguzcan.paketlerdemo.repository.PacketRepository;
import com.oguzcan.paketlerdemo.repository.PurchaseHistoryRepository;
import com.oguzcan.paketlerdemo.services.admin.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final CustomerRepository customerRepository;
    private final PacketRepository packetRepository;
    private final PurchaseHistoryRepository purchaseHistoryRepository;
    private final ModelMapper modelMapper;

    public AdminServiceImpl(AdminRepository adminRepository, CustomerRepository customerRepository, PacketRepository packetRepository, PurchaseHistoryRepository purchaseHistoryRepository, ModelMapper modelMapper) {
        this.adminRepository = adminRepository;
        this.customerRepository = customerRepository;
        this.packetRepository = packetRepository;
        this.purchaseHistoryRepository = purchaseHistoryRepository;
        this.modelMapper = modelMapper;
    }

    // ADMIN ############################################################################################################

    public Result addAdmin(AdminDto adminDto) {
        Admin admin = modelMapper.map(adminDto, Admin.class);
        adminRepository.save(admin);
        return new SuccessResult("Admin başarıyla oluşturuldu.");
    }


    // USER ############################################################################################################
    @Override
    public Result addCustomer(CustomerDto customerDto) {
        Customer customer = modelMapper.map(customerDto, Customer.class);
        PersonalInformation personalInformation = modelMapper.map(customerDto, PersonalInformation.class);
        customer.setCreateAt(ZonedDateTime.now());
        customer.setPersonalInformation(personalInformation);
        customer.setPacketBalance(new PacketBalance());

            System.out.println(customerDto);
            System.out.println(customer);

        customerRepository.save(customer);
        return new SuccessResult("Müşteri başarıyla oluşturuldu.");
    }

    @Override
    public DataResult<CustomerDto> getCustomer(Long userId) {
        Optional<Customer> customer = this.customerRepository
                .findById(userId);

        if (customer.isPresent()) {
            CustomerDto customerDto = modelMapper.map(customer, CustomerDto.class);
//            customerDto.setFirstname(customer.get().getPersonalInformation().getFirstname());

            return new SuccessDataResult<CustomerDto>(customerDto, "Müşteri getirildi.");
        }
        return new ErrorDataResult<CustomerDto>("Müşteri bulunamadı.");
    }

    @Override
    public DataResult<List<CustomerDto>> getCustomers() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerDto> customerDtos = customers.stream()
                .map(customer -> modelMapper.map(customer, CustomerDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<CustomerDto>>
                (customerDtos, "Tüm müşteriler getirildi.");
    }

    @Override
    public Result updateCustomer(CustomerDto customerDto) {
        Optional<Customer> customer = this.customerRepository
                .findById(customerDto.getUserId());

        if (customer.isPresent()) {
            Customer updatedCustomer = customer.get();
            updatedCustomer = modelMapper.map(customerDto, Customer.class);
            updatedCustomer.setUpdateAt(ZonedDateTime.now());
            customerRepository.save(updatedCustomer);
            return new SuccessResult("Müşteri basariyla güncellendi.");}
        return new ErrorResult("Müşteri güncellemesi başarısız.");
    }

    @Override
    public Result deleteCustomer(Long userId) {
        Optional<Customer> customer = this.customerRepository
                .findById(userId);

        if (customer.isPresent()) {
            this.customerRepository.deleteById(userId);
            return new SuccessResult("Müşteri başarıyla silindi.");
        }
        return new ErrorResult("Müşteri silinmesinde hata oluştu. Müşteri bulunamadı.");
    }

    // PACKET ##########################################################################################################

    @Override
    public Result addPacket(PacketDto packetDto, String adminFullName) {
        Packet packet = this.modelMapper.map(packetDto, Packet.class);
        packet.setCreateAt(ZonedDateTime.now());
        packet.setCreateBy(adminFullName);
        this.packetRepository.save(packet);
        return new SuccessResult(packet.getTitle() + " adlı paket başarıyla oluşturuldu.");
    }
    @Override
    public Result deletePacketId(Long packetId) {
        Optional<Packet> packet = packetRepository.findById(packetId);
        if (packet.isPresent()) {
            packetRepository.deleteById(packetId);
            return new SuccessResult("Paket silme başarılı.");
        }
        return new ErrorResult("Paket silme başarısız.");
    }

    @Override
    public DataResult<List<Packet>> getAllPackets() {
        return new SuccessDataResult<List<Packet>>
                (this.packetRepository.findAll(), "Paketler listelendi");
    }

    @Override
    public DataResult<Packet> getByIdPacket(Long packetId) {
        Optional<Packet> packet = this.packetRepository.findById(packetId);
        if (packet.isPresent()) {
            return new SuccessDataResult<Packet>(packet.get(), "Paket getirildi.");
        }
        return new ErrorDataResult<Packet>("Paket bulunamadı.");
    }

    @Override
    public Result updatePacket(Long packetId, PacketDto packetDto, String adminFullName) {
        Optional<Packet> resultPaket = packetRepository.findById(packetId);
        if (resultPaket.isPresent()) {
            resultPaket.get().setTitle(packetDto.getTitle());
            resultPaket.get().setPrice(packetDto.getPrice());
            resultPaket.get().setData(packetDto.getData());
            resultPaket.get().setMinutes(packetDto.getMinutes());
            resultPaket.get().setSms(packetDto.getSms());
            resultPaket.get().setUpdateAt(ZonedDateTime.now());
            resultPaket.get().setUpdateBy(adminFullName);
            this.packetRepository.save(resultPaket.get());
            return new SuccessResult("Paket güncelleme başarılı.");
        }
        return new ErrorResult("Paket güncelleme başarısız.");
    }
}
