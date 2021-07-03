package com.audit.util;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * The Class JSONUtil.
 */
@Service
public class JSONUtil {

	/** The object writer. */
	private static ObjectWriter objectWriter;

	/** The object mapper. */
	private static ObjectMapper objectMapper;

	private static ObjectMapper objectMapperWithoutIgnoreUnknownFields;

	private static ObjectMapper objectMapperIncludeRootElement;


	/**
	 * Gets the object writer.
	 *
	 * @return the object writer
	 */
	@SuppressWarnings("deprecation")
	public static ObjectWriter getObjectWriter() {
		if (null == objectWriter) {
			ObjectMapper objectMapper = new ObjectMapper();
			VisibilityChecker<?> defaultVisibilityChecker = objectMapper.getSerializationConfig().getDefaultVisibilityChecker();
			objectMapper.setVisibility(defaultVisibilityChecker.withFieldVisibility(JsonAutoDetect.Visibility.ANY));
			DateFormat df = new SimpleDateFormat("MM-dd-yyyy HH:mm");
			objectMapper.setDateFormat(df);
			objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
		}
		return objectWriter;
	}

	/**
	 * Gets the object mapper.
	 *
	 * @return the object mapper
	 */
	@SuppressWarnings("deprecation")
	public ObjectMapper getObjectMapper() {
		if (null == objectMapper) {
			objectMapper = new ObjectMapper();
		}
		return objectMapper;
	}

	public static ObjectMapper getObjectMapperWithoutIgnoreUnknownFields() {
		if(null == objectMapperWithoutIgnoreUnknownFields) {
			objectMapperWithoutIgnoreUnknownFields = new ObjectMapper();
			objectMapperWithoutIgnoreUnknownFields.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		}
		return objectMapperWithoutIgnoreUnknownFields;
	}

	/**
	 * Convert object to json string.
	 *
	 * @param object
	 *            the object
	 * @return the string
	 */
	public String convertObjectToJsonString(Object object) {
		try {
			return getObjectMapper().writeValueAsString(object);
		} catch (IOException e) {
			//logger.error(e);
		}
	//	return NMSConstant.JSON_CONVERSION_ERROR;
		return null;
	}

	/**
	 * @param objectMapperIncludeRootElement the objectMapperIncludeRootElement to set
	 */
	public static void setObjectMapperIncludeRootElement(ObjectMapper objectMapperIncludeRootElement) {
		JSONUtil.objectMapperIncludeRootElement = objectMapperIncludeRootElement;
	}
}
