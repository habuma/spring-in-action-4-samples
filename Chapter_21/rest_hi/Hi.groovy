@RestController
class Hi {
	@RequestMapping("/")
	String hi() {
		"Hi!"
	}
}