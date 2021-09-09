package com.ideas2it.vitalsignservicemongo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.annotation.EnableCaching;

import com.ideas2it.vitalsignservicemongo.client.PatientClient;
import com.ideas2it.vitalsignservicemongo.dto.VitalSignDto;
import com.ideas2it.vitalsignservicemongo.dto.VitalSignPrimaryKeys;
import com.ideas2it.vitalsignservicemongo.entity.PatientEntity;
import com.ideas2it.vitalsignservicemongo.entity.VitalSignEntity;
import com.ideas2it.vitalsignservicemongo.repository.VitalSignRepository;
import com.ideas2it.vitalsignservicemongo.service.VitalSignService;
import com.ideas2it.vitalsignservicemongo.util.EntityAndDtoConverter;

@SpringBootTest
@EnableCaching
class VitalsignServiceTest {

	@Autowired
	public VitalsignServiceTest(VitalSignService vitalSignservice) {
		this.vitalSignservice = vitalSignservice;
	}

	private VitalSignService vitalSignservice;

	@MockBean
	private VitalSignRepository vitalSignrepository;

	@MockBean
	private PatientClient patientClient;

	@Test
	public void getVitalSignByIdServiceTest() throws ParseException {
		VitalSignPrimaryKeys vitalSignPrimaryKeys = new VitalSignPrimaryKeys();
		vitalSignPrimaryKeys.setPatientId("fcb59852-5655-4d75-ab68-e7f92b387520");
		String sDate1 = "2015-03-31";
		vitalSignPrimaryKeys.setLastVisitDate(sDate1);

		when(vitalSignrepository.findById(any(VitalSignPrimaryKeys.class)))
				.thenReturn(Optional.of(new VitalSignDto(vitalSignPrimaryKeys, 120, 100, 240, "Genearl checkup")));

		assertNotEquals(null, vitalSignservice.getVitalSignById(vitalSignPrimaryKeys.getPatientId().toString(),
				vitalSignPrimaryKeys.getLastVisitDate()));
		assertEquals(vitalSignPrimaryKeys.getPatientId(),
				vitalSignservice.getVitalSignById(vitalSignPrimaryKeys.getPatientId().toString(),
						vitalSignPrimaryKeys.getLastVisitDate()).getPatientId());

	}

	@Test
	public void saveVitalSignServiceTest() {
		VitalSignEntity vitalSignEntity = new VitalSignEntity("fcb59852-5655-4d75-ab68-e7f92b387520", "2015-03-31", 120,
				100, 240, "Genearl checkup");
		when(patientClient.getById(any())).thenReturn(new PatientEntity.patientEntityBuiler()
				.patientID(UUID.fromString("fcb59852-5655-4d75-ab68-e7f92b387520")).patientName("").build());
		when(vitalSignrepository.save(EntityAndDtoConverter.formDto(vitalSignEntity)))
				.thenReturn(EntityAndDtoConverter.formDto(vitalSignEntity));
		assertTrue(vitalSignservice.save(vitalSignEntity).isResult());
	}

	@Test
	public void saveVitalSignServiceFailTest() {
		VitalSignEntity vitalSignEntity = new VitalSignEntity("fcb59852-5655-4d75-ab68-e7f92b387520", "2015-03-31", 120,
				100, 240, "Genearl checkup");
		when(patientClient.getById(any())).thenReturn(new PatientEntity.patientEntityBuiler()
				.patientID(UUID.fromString("fcb59852-5655-4d75-ab68-e7f92b387520")).patientName("").build());
		when(patientClient.getById(vitalSignEntity.getPatientId().toString()) == null).thenReturn(false);
		assertFalse(vitalSignservice.save(vitalSignEntity).isResult());
	}
	
	@Test
	public void deleteByIdServiceTest() {
		VitalSignPrimaryKeys vitalSignPrimaryKeys = new VitalSignPrimaryKeys();
		vitalSignPrimaryKeys.setPatientId("fcb59852-5655-4d75-ab68-e7f92b387520");
		String sDate1 = "2015-03-31";
		vitalSignPrimaryKeys.setLastVisitDate(sDate1);

		List<VitalSignDto> vitalSignDtoList = new ArrayList<VitalSignDto>();
		vitalSignDtoList.add(new VitalSignDto(vitalSignPrimaryKeys, 120, 100, 240, "Genearl checkup"));

		when(vitalSignrepository.findById(anyString())).thenReturn(vitalSignDtoList);
		vitalSignservice.deleteById(vitalSignPrimaryKeys.getPatientId().toString());
		verify(vitalSignrepository, times(1)).deleteById(vitalSignPrimaryKeys.getPatientId().toString());
	}

	@Test
	public void searchTxtTest() {
		VitalSignPrimaryKeys vitalSignPrimaryKeys = new VitalSignPrimaryKeys();
		vitalSignPrimaryKeys.setPatientId("fcb59852-5655-4d75-ab68-e7f92b387520");
		vitalSignPrimaryKeys.setLastVisitDate("2015-03-31");
		List<VitalSignDto> vitalSignDtoList = new ArrayList<VitalSignDto>();
		vitalSignDtoList.add(new VitalSignDto(vitalSignPrimaryKeys, 120, 100, 240, "Genearl checkup"));
		when(vitalSignrepository.search(anyString())).thenReturn(vitalSignDtoList);
		assertEquals(1, vitalSignservice.searchTxt("vicky").size());
	}

	@Test
	void contextLoads() {

	}

}
